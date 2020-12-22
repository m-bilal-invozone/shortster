package com.example.urlShortner.controller;

import com.example.urlShortner.dto.ShortUrlCreationRequestDTO;
import com.example.urlShortner.dto.ShortUrlStatsResponseDTO;
import com.example.urlShortner.exception.ApiRequestException;
import com.example.urlShortner.service.UrlShortnerService;
import com.example.urlShortner.validator.ShortCodeValidator;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(value = "/urlshortner")
public class UrlShortnerController {

    @Autowired
    UrlShortnerService urlShortnerService;

    Logger logger = LogManager.getLogger(UrlShortnerController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> encodeUrl(@RequestBody ShortUrlCreationRequestDTO shortUrlCreationRequestDTO){

        logger.debug("EncodeUrl method with data :"+shortUrlCreationRequestDTO.toString());
        if(shortUrlCreationRequestDTO.isValid()){
            try {
                if (shortUrlCreationRequestDTO.getShortCode() != null
                        && !ShortCodeValidator.isShortCodeValid(shortUrlCreationRequestDTO.getShortCode())) {
                    throw new ApiRequestException("Invalid Short Code", HttpStatus.BAD_REQUEST);
                }

                URI redirectUrl= new URI(shortUrlCreationRequestDTO.getOriginalUrl());
                String shortUrl = urlShortnerService.encodeUrl(shortUrlCreationRequestDTO);
                logger.debug("Exiting EncodeUrl method with data :"+shortUrl);
                return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);
            }catch (URISyntaxException exception){
                throw new ApiRequestException("Invalid Url", HttpStatus.INTERNAL_SERVER_ERROR);
            }catch(Exception ex){
                throw new ApiRequestException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>("OriginalUrl should not be empty", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{shortcode}", method = RequestMethod.GET)
    public ResponseEntity<Object> getDecodedUrl(@PathVariable("shortcode") String shortcode) {

        logger.debug("getDecodedUrl method with data :"+shortcode);
        if(!ShortCodeValidator.isShortCodeValid(shortcode)){
            throw new ApiRequestException("Invalid Short Code", HttpStatus.BAD_REQUEST);
        }
        URI redirectUrl = null;
        try{
            String originalUrl = urlShortnerService.getDecodedUrl(shortcode);
            redirectUrl= new URI(originalUrl);
        }catch (URISyntaxException exception){
            throw new ApiRequestException("Invalid Url", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (ObjectNotFoundException ex){
            throw new ApiRequestException(ex.getMessage(), HttpStatus.NOT_FOUND);
        }catch(NoSuchElementException ex){
            throw new ApiRequestException(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        logger.debug("Existing getDecodedUrl method with redirectUrl :"+redirectUrl);
        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.setLocation(redirectUrl);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER );

    }

    @RequestMapping(value = "/{shortcode}/stats", method = RequestMethod.GET)
    public ResponseEntity<ShortUrlStatsResponseDTO> getStats(@PathVariable("shortcode") String shortcode) {

        logger.debug("getStats method with data :"+shortcode);
        if(!ShortCodeValidator.isShortCodeValid(shortcode)){
            throw new ApiRequestException("Invalid Short Code", HttpStatus.BAD_REQUEST);
        }
        ShortUrlStatsResponseDTO shortUrlStatsResponseDTO = null;

        try {
            shortUrlStatsResponseDTO = urlShortnerService.getStats(shortcode);
        }catch (ObjectNotFoundException ex){
            throw new ApiRequestException(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        logger.debug("Exiting getStats method with data :"+shortUrlStatsResponseDTO.toString());
        return new ResponseEntity<>(shortUrlStatsResponseDTO, HttpStatus.OK);
    }
}
