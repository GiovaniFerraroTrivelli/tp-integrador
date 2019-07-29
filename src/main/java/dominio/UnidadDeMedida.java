package dominio;

public enum UnidadDeMedida {
	KILO("kilogramo(s)"),
	PIEZA("pieza(s)"),
	GRAMO("gramo(s)"),
	METRO("metro(s)"),
	LITRO("litro(s)"),
	M3("metro(s) cúbico(s)"),
	M2("metro(s) cuadrado(s)"),
	CM2("centímetro(s) cuadrado(s)"),
	CM("centímetro(s)");
	
	private String desc;
	public String getDesc() { return this.desc; } 
	private UnidadDeMedida(String desc) { this.desc = desc; }
	
	@Override
	public String toString() { return this.desc; }
}
