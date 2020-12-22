package com.example.urlShortner.repository;
import com.example.urlShortner.entity.UrlEntry;
import org.springframework.data.repository.CrudRepository;

public interface UrlShortnerRepository extends CrudRepository<UrlEntry, String> {

}
