package clientDemo;

import org.springframework.web.client.RestTemplate;

import com.example.jpa.entity.Product;

public class MyRestClientDemo {

	private static final String restServiceURI="http://localhost:7073";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getRecordById();
	}
	
	private static void getRecordById() {
		Product product = new RestTemplate().getForObject(restServiceURI+"/read/all", Product.class);
				System.out.println(product);
	}

}
