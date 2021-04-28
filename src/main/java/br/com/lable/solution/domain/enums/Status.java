package br.com.lable.solution.domain.enums;

public enum Status {

	Vendido(1, "Vendido"), Disponivel(2, "Disponível");

	private int codigo;
	private String descricao;

	private Status(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Status toEnum(Integer codigo) {
		if(codigo == null) return null;
		for(Status s : Status.values()) {
			if(codigo.equals(s.getCodigo())) {
				return s;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

}

