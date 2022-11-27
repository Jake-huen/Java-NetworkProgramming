package problem;


public class Product {
	private Long order_id; // 상품 번호
	private String admin_id; // 관리자 번호
	private String name; // 상품명
	private String status; // 배송 상태
	private String created_at; // 생성 시간

	
	public Product(){
		
	}
	public Product(Long orderId, String adminId,String name, String status, String createdAt) {
		this.order_id = orderId;
		this.admin_id=adminId;
		this.name = name;
		this.status = status;
		this.created_at = createdAt;
	}
	
	public Product(Long orderId, String name, String status, String createdAt) {
		this.order_id = orderId;
		this.name = name;
		this.status = status;
		this.created_at = createdAt;
	}
	
	public Product(String adminId,String name){
		this.name=name;
		this.admin_id=adminId;
		this.status="start";
	}
	
	public Product(Long orderId, String name, String status) {
		this.order_id  = orderId;
		this.name = name;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Product [orderId=" + order_id  + ", name=" + name + ", status=" + status + ", createdAt=" + created_at + "]";
	}
	public Long getOrderId() {
		return order_id;
	}
	public String getAdminId() {
		return admin_id;
	}
	public String getName() {
		return name;
	}
	public String getStatus() {
		return status;
	}
	public String getCreatedAt() {
		return created_at;
	}

	public static Product toAddProduct(){
		return null;
	}
	
}
