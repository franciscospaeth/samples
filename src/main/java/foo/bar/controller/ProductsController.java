package foo.bar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import foo.bar.dao.ProductDao;
import foo.bar.model.Product;

/**
 * Controller para a aplicação, que tem por objetivo expor algumas funcionalidades básicas da aplicação.
 * 
 * @author Francisco Spaeth
 */
@RestController
public class ProductsController {

	private ProductDao productDao;

	@Autowired
	public ProductsController(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * Curl request:
	 * curl -XPUT http://localhost:8080/ -H "Content-Type: application/json" -d '{
	 *   "available": true,
	 *   "popularity": 1,
	 *   "id": "5f5fe376-c596-11e4-8c80-f0defa6c59f4",
	 *   "price": 1250,
	 *   "price_c": "1250.0,USD",
	 *   "category": "Periféricos",
	 *   "name": "Monitor 17\"",
	 *   "description": "Monitor com caixas de som incluídas, definição UHD."
	 * }'
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void save(@RequestBody Product product) {
		productDao.save(product);
	}

	/**
	 * Curl request:
	 * curl -XDELETE "http://localhost:8080/5f5fe376-c596-11e4-8c80-f0defa6c59f4/"
	 */
	@RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		productDao.delete(id);
	}

	/**
	 * Curl request:
	 * curl "http://localhost:8080/"
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<Product> get() {
		return productDao.findAll(new SolrPageRequest(0, 20));
	}

	/**
	 * Curl request:
	 * curl "http://localhost:8080/5f5fe376-c596-11e4-8c80-f0defa6c59f4/"
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product get(String id) {
		return productDao.findOne(id);
	}

	/**
	 * Curl request:
	 * curl "http://localhost:8080/list/Periféricos/0/20/"
	 */
	@RequestMapping(value = "/list/{category}/{page}/{size}", method = RequestMethod.GET)
	public Page<Product> listByCategoryAndAvailability( //
			@PathVariable("category") String category, //
			@PathVariable("page") int page, //
			@PathVariable("size") int size //
	) {
		return productDao.findByCategoryAndAvailableTrue(category, new SolrPageRequest(page, size));
	}

	/**
	 * Curl request:
	 * curl "http://localhost:8080/search/caixas%20monitor/0/20/"
	 */
	@RequestMapping(value = "/search/{searchString}/{page}/{size}", method = RequestMethod.GET)
	public FacetPage<Product> search( //
			@PathVariable("searchString") String searchString, //
			@PathVariable("page") int page, //
			@PathVariable("size") int size //
	) {
		return productDao.find(searchString, new SolrPageRequest(page, size));
	}

}