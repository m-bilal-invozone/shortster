package com.example.urlShortner.assembler;

import com.example.urlShortner.dto.ShortUrlCreationRequestDTO;
import com.example.urlShortner.dto.ShortUrlStatsResponseDTO;
import com.example.urlShortner.entity.UrlEntry;

import java.sql.Date;

public class Assembler {
    public static UrlEntry FromShortUrlCreationRequestDTOToUrl(ShortUrlCreationRequestDTO shortUrlCreationRequestDTO){
        UrlEntry url = null;
        if(shortUrlCreationRequestDTO != null){
            url = new UrlEntry();
            url.setOriginalUrl(shortUrlCreationRequestDTO.getOriginalUrl());
        }
        return url;
    }

    public static ShortUrlStatsResponseDTO FromUrlEntryToShortUrlStatsResponseDTO(UrlEntry urlEntry){
        ShortUrlStatsResponseDTO shortUrlStatsResponseDTO = null;
        if(urlEntry != null){
            shortUrlStatsResponseDTO = new ShortUrlStatsResponseDTO();
            shortUrlStatsResponseDTO.setOrignalUrl(urlEntry.getOriginalUrl());
            shortUrlStatsResponseDTO.setCreationDate(urlEntry.getCreated_At().toLocalDateTime().toString());
            shortUrlStatsResponseDTO.setLastAccessed(urlEntry.getLastAccessTime().toLocalDateTime().toString());
            shortUrlStatsResponseDTO.setAccessCount(urlEntry.getAccessCount());
        }
        return shortUrlStatsResponseDTO;
    }
}
