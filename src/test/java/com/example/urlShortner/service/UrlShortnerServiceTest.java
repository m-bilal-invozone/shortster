package com.example.urlShortner.service;

import com.example.urlShortner.dto.ShortUrlCreationRequestDTO;
import com.example.urlShortner.dto.ShortUrlStatsResponseDTO;
import com.example.urlShortner.entity.UrlEntry;
import com.example.urlShortner.repository.UrlShortnerRepository;
import com.example.urlShortner.service.UrlShortnerService;
import com.example.urlShortner.util.UrlEncodeDecoderUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Optional;

@SpringBootTest
public class UrlShortnerServiceTest {
    @MockBean
    UrlShortnerRepository urlShortnerRepository;

    @Autowired
    UrlShortnerService urlShortnerService;


    private final String ORIGINAL_URL = "https://www.youtube.com/";
    private final String SHORT_CODE = "AAAAAA";
    private final int ACCESS_COUNT = 1;

    @Test
    void encodeUrlTestWithNoShortCode(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        UrlEntry urlEntry = new UrlEntry();
        urlEntry.setId(SHORT_CODE);
        urlEntry.setOriginalUrl(ORIGINAL_URL);
        urlEntry.setAccessCount(ACCESS_COUNT);
        urlEntry.setCreated_At(timestamp);
        urlEntry.setLastAccessTime(timestamp);

        ShortUrlCreationRequestDTO shortUrlCreationRequestDTO =
                new ShortUrlCreationRequestDTO();
        shortUrlCreationRequestDTO.setOriginalUrl(ORIGINAL_URL);

        BigInteger num = BigInteger.valueOf(timestamp.getTime());
        Mockito.when(urlShortnerRepository.save(Mockito.any(UrlEntry.class))).thenReturn(urlEntry);
        Mockito.when(urlShortnerRepository.findById(SHORT_CODE)).thenReturn(Optional.empty());
        String resultShortCode = null;
        try {
             resultShortCode = urlShortnerService.encodeUrl(shortUrlCreationRequestDTO);
        }catch (Exception ex){}
        Assertions.assertThat(resultShortCode).isEqualTo(SHORT_CODE);
    }

    @Test
    void encodeUrlTestWithShortCode(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        UrlEntry urlEntry = new UrlEntry();
        urlEntry.setId(SHORT_CODE);
        urlEntry.setOriginalUrl(ORIGINAL_URL);
        urlEntry.setAccessCount(ACCESS_COUNT);
        urlEntry.setCreated_At(timestamp);
        urlEntry.setLastAccessTime(timestamp);

        ShortUrlCreationRequestDTO shortUrlCreationRequestDTO =
                new ShortUrlCreationRequestDTO();
        shortUrlCreationRequestDTO.setOriginalUrl(ORIGINAL_URL);
        shortUrlCreationRequestDTO.setShortCode(SHORT_CODE);

        Mockito.when(urlShortnerRepository.save(Mockito.any(UrlEntry.class))).thenReturn(urlEntry);
        Mockito.when(urlShortnerRepository.findById(SHORT_CODE)).thenReturn(Optional.empty());
        String resultShortCode = null;
        try {
            resultShortCode = urlShortnerService.encodeUrl(shortUrlCreationRequestDTO);
        }catch (Exception ex){}
        Assertions.assertThat(resultShortCode).isEqualTo(SHORT_CODE);
    }

    @Test
    void getDecodedUrlTest(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        UrlEntry urlEntry = new UrlEntry();
        urlEntry.setId(SHORT_CODE);
        urlEntry.setOriginalUrl(ORIGINAL_URL);
        urlEntry.setAccessCount(ACCESS_COUNT);
        urlEntry.setCreated_At(timestamp);
        urlEntry.setLastAccessTime(timestamp);

        Mockito.when(urlShortnerRepository.save(Mockito.any(UrlEntry.class))).thenReturn(urlEntry);
        Mockito.when(urlShortnerRepository.findById(SHORT_CODE)).thenReturn(Optional.of(urlEntry));
        String  originalUrl = null;
        originalUrl = urlShortnerService.getDecodedUrl(SHORT_CODE);
        Assertions.assertThat(originalUrl).isEqualTo(ORIGINAL_URL);
        Mockito.verify(urlShortnerRepository).save(Mockito.any(UrlEntry.class));
    }

    @Test
    void getStatsTest(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        UrlEntry urlEntry = new UrlEntry();
        urlEntry.setId(SHORT_CODE);
        urlEntry.setOriginalUrl(ORIGINAL_URL);
        urlEntry.setAccessCount(ACCESS_COUNT);
        urlEntry.setCreated_At(timestamp);
        urlEntry.setLastAccessTime(timestamp);

        Mockito.when(urlShortnerRepository.findById(SHORT_CODE)).thenReturn(Optional.of(urlEntry));
        ShortUrlStatsResponseDTO shortUrlStatsResponseDTO = null;
        shortUrlStatsResponseDTO = urlShortnerService.getStats(SHORT_CODE);

        Assertions.assertThat(shortUrlStatsResponseDTO.getAccessCount()).isEqualTo(ACCESS_COUNT);
        Assertions.assertThat(shortUrlStatsResponseDTO.getOrignalUrl()).isEqualTo(ORIGINAL_URL);
        Assertions.assertThat(shortUrlStatsResponseDTO.getCreationDate()).isEqualTo(timestamp.toLocalDateTime().toString());
        Assertions.assertThat(shortUrlStatsResponseDTO.getLastAccessed()).isEqualTo(timestamp.toLocalDateTime().toString());
    }

}
