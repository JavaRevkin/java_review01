package model;

public class Product {

	private int productCode;			// 製品コード
	private String productName;			// 製品名
	private int price;					// 価格
	private String registerDatetime;	// 登録日時
	private String updateDatetime;		// 更新日時
	private String deleteDatetime;		// 削除日時

	// デフォルトコンストラクタ
	public Product() {
		super();
	}

	// 製品名と価格を指定してインスタンスを初期化するコンストラクタ
	public Product(String productName, int price) {
		super();
		this.productName = productName;
		this.price = price;
	}
	
	/*
	 * GetterとSetterメソッド
	 */
	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRegisterDatetime() {
		return registerDatetime;
	}

	public void setRegisterDatetime(String string) {
		this.registerDatetime = string;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getDeleteDatetime() {
		return deleteDatetime;
	}

	public void setDeleteDatetime(String deleteDatetime) {
		this.deleteDatetime = deleteDatetime;
	}

}
