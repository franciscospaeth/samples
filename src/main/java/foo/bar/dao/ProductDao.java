package foo.bar.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import foo.bar.model.Product;

/**
 * Repositório de produtos ({@link Product}) que extende de {@link SolrCrudRepository} para fazer uso dos métodos já
 * declarados como: save, delete, etc.
 * 
 * @author Francisco Spaeth
 */
public interface ProductDao extends SolrCrudRepository<Product, String> {

	@Query("*:*")
	Page<Product> findAllPaged(Pageable pageRequest);

	Page<Product> findByCategoryAndAvailableTrue(String category, Pageable pageRequest);

	@Facet(fields = "category", minCount = 1)
	@Query("title:?0 description:?0")
	FacetPage<Product> find(String searchString, Pageable pageRequest);

}