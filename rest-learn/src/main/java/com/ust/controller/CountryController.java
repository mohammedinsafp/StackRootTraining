package com.ust.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.model.Country;
import com.ust.service.CountryService;


@RestController
public class CountryController {

	@Autowired
	CountryService countryService;		
	
	@GetMapping("/countries/{code}") 
	public Country getCountry(@PathVariable String code) {
		
		Country country= countryService.findCountryByCode(code);	
				
		return country;
	}
	
	@GetMapping("/countries") 
	public List<Country> getAllCountries() {
		
	List<Country> countries= countryService.getAllCountry();
		
		return countries;		
		
	}
	
	@PostMapping("/countries")
	public Country createCountry(@RequestBody Country country) {
		
		countryService.addCountry(country);
				
		return country;		
		
	}
	
	@DeleteMapping("/countries/{code}")
	public ResponseEntity<Country> deleteCountry(@PathVariable String code) {
		countryService.deleteCountry(code);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		}
	
	
	@PutMapping("/countries/{code}") 
	public Country updateCountry(@PathVariable String code,@RequestBody Country country ) {
	
		countryService.updateCountry(code,country.getName()) ;
				
		return country;
		}
}
