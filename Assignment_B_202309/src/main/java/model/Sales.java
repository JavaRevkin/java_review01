package model;

public class Sales {
	private int productCode;			// 製品コード
	private int quantity;				// 数量
	private String registerDatetime;	// 登録日時
	private String updateDatetime;		// 更新日時
	private String salesDate;			// 販売日
	private String productName;			// 製品名
	private int price;					// 価格
	// GetterとSetterメソッド
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRegisterDatetime() {
		return registerDatetime;
	}

	public void setRegisterDatetime(String registerDatetime) {
		this.registerDatetime = registerDatetime;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	// 製品コード、数量、販売日を指定してインスタンスを初期化するコンストラクタ
	public Sales(int productCode, int quantity, String salesDate) {
		super();
		this.productCode = productCode;
		this.quantity = quantity;
		this.salesDate = salesDate;
	}

	// 製品コード、数量、販売日、製品名を指定してインスタンスを初期化するコンストラクタ
	public Sales(int productCode, int quantity, String salesDate, String productName) {
		super();
		this.productCode = productCode;
		this.quantity = quantity;
		this.salesDate = salesDate;
		this.productName = productName;
	}
	

	// デフォルトコンストラクタ
	public Sales() {
		super();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	

}
