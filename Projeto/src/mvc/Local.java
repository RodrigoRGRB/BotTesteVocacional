package mvc;

public class Local {
	  Double latitude;
	  Double longitude;
	  String title;
	  String endereco;

	  public Local() {  //construtor
	    this.latitude = 0.00;
	    this.longitude = 0.00;
	    this.title = "";
	    this.endereco = "";
	  }
	  // setters e getters aqui

	public Double getlatitude() {
		return latitude;
	}

	public void setlatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	  
	}