package br.com.infosys.moviedb.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.infosys.moviedb.core.services.WriterService;
import br.com.infosys.moviedb.domain.entities.Writer;

/**
 * Writer resource API.
 * 
 * @author vitor191291@gmail.com
 *
 */
@RestController
@RequestMapping("${api.url.writer}")
public class WriterController {

	private static final Logger logger = LoggerFactory.getLogger(WriterController.class);

	private WriterService writerService;

	@Autowired
	public WriterController(WriterService writerService) {
		this.writerService = writerService;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Writer> createWriter(@RequestBody Writer writer) {
		logger.info("Creating Writer with name " + writer.getName());

		if (writerService.exists(writer.getIdWriter())) {
			logger.info("Writer with id " + writer.getIdWriter() + " already exist!");
			return new ResponseEntity<Writer>(HttpStatus.CONFLICT);
		}

		Writer persistedWriter = writerService.save(writer);

		return new ResponseEntity<Writer>(persistedWriter, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Writer> updateWriter(@PathVariable("id") Long id, @RequestBody Writer writer) {
		logger.info("Updating Writer " + id);
		
		if (!writerService.exists(id)) {
			logger.info("Writer with id " + id + " not found!");
			return new ResponseEntity<Writer>(HttpStatus.NOT_FOUND);
		}
		
		Writer updatedWriter = writerService.update(id, writer);
		
		return new ResponseEntity<Writer>(updatedWriter, HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Writer> deleteWriter(@PathVariable("id") Long id) {
		logger.info("Deleting Writer " + id);

		if (!writerService.exists(id)) {
			logger.info("Writer with id " + id + " not found!");
			return new ResponseEntity<Writer>(HttpStatus.NOT_FOUND);
		}

		writerService.deleteById(id);

		return new ResponseEntity<Writer>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Writer> deleteAllWriters() {
		logger.info("Deleting all Writers");

		writerService.deleteAll();

		return new ResponseEntity<Writer>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Writer> getWriter(@PathVariable("id") Long id) {
		logger.info("Fetching Writer with id " + id);

		Writer writer = writerService.findById(id);

		if (writer == null) {
			logger.info("Writer with id " + id + " not found!");
			return new ResponseEntity<Writer>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Writer>(writer, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Writer>> getAllWriters() {
		logger.info("Fetching all Writers");

		List<Writer> writers = writerService.findAll();

		if (writers.isEmpty()) {
			return new ResponseEntity<List<Writer>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Writer>>(writers, HttpStatus.OK);
	}

}
