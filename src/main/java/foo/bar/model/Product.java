package foo.bar.model;

import java.util.UUID;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * Classe de modelo para produto. Nota-se aqui que {@link SolrDocument} tem a definição do índice no qual o doducmentos
 * deste tipo devem ser armazenados, assim como a definição dos campos deste tipo de documento.
 * 
 * @author Francisco Spaeth
 */
@SolrDocument(solrCoreName = "products")
public class Product {

	@Id @Field private String id = UUID.randomUUID().toString();
	@Field private String name;
	@Field private String description;
	@Field private String category;
	@Field private float price;
	@Field private int popularity;
	@Field private boolean inStock;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

}
