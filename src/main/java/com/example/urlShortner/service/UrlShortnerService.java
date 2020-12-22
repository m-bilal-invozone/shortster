package com.example.urlShortner.service;

import com.example.urlShortner.assembler.Assembler;
import com.example.urlShortner.controller.UrlShortnerController;
import com.example.urlShortner.dto.ShortUrlCreationRequestDTO;
import com.example.urlShortner.dto.ShortUrlStatsResponseDTO;
import com.example.urlShortner.entity.UrlEntry;
import com.example.urlShortner.repository.UrlShortnerRepository;
import com.example.urlShortner.util.UrlEncodeDecoderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UrlShortnerService {

    @Autowired
    UrlShortnerRepository urlShortnerRepository;

    Logger logger = LogManager.getLogger(UrlShortnerController.class);

    public String encodeUrl(ShortUrlCreationRequestDTO shortUrlCreationRequestDTO) throws Exception {

        logger.debug("Getting inside encodeUrl with data :"+shortUrlCreationRequestDTO.toString());
        Boolean isInserted = Boolean.FALSE;
        String finalShortCode = null;

        do{
            String shortCode = null;
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            BigInteger num = BigInteger.valueOf(timestamp.getTime());

            if(shortUrlCreationRequestDTO.getShortCode() == null) {
                shortCode = UrlEncodeDecoderUtil.generateShortCode(num);
            }else{
                shortCode = shortUrlCreationRequestDTO.getShortCode();
            }

            synchronized(urlShortnerRepository) {
                Optional<UrlEntry> urlEntry = urlShortnerRepository.findById(shortCode);
                if (!urlEntry.isPresent()) {
                    UrlEntry url = Assembler.FromShortUrlCreationRequestDTOToUrl(shortUrlCreationRequestDTO);
                    url.setId(shortCode);
                    url = urlShortnerRepository.save(url);
                    logger.debug(url.toString());
                    finalShortCode = url.getId();
                    isInserted = Boolean.TRUE;
                    logger.debug("encodeUrl - Entry inserted");
                }else{
                    if(shortUrlCreationRequestDTO.getShortCode() != null) {
                        throw new Exception("Key already exists");
                    }
                }
            }
        }while(!isInserted);

        logger.debug("Exiting encodeUrl method with short code : "+finalShortCode);
        return finalShortCode;
    }

    public String getDecodedUrl(String shortUrl){

        logger.debug("Getting inside getDecodedUrl - shortUrl : "+ shortUrl);
        Optional<UrlEntry> urlEntry = urlShortnerRepository.findById(shortUrl);

        UrlEntry url = urlEntry.get();
        url.setAccessCount(url.getAccessCount() + 1);
        urlShortnerRepository.save(url);

        logger.debug("Exiting getDecodedUrl with original url : "+urlEntry.get().getOriginalUrl());
        return urlEntry.get().getOriginalUrl();
    }

    public ShortUrlStatsResponseDTO getStats(String shortUrl){

        logger.debug("Getting inside getStats - shortUrl : "+shortUrl);
        Optional<UrlEntry> urlEntry = urlShortnerRepository.findById(shortUrl);
        ShortUrlStatsResponseDTO shortUrlStatsResponseDTO = Assembler.FromUrlEntryToShortUrlStatsResponseDTO(urlEntry.get());
        logger.debug("Exiting getStats - with data : "+shortUrlStatsResponseDTO.toString());
        return shortUrlStatsResponseDTO;
    }

}
