package controle;


public class Relatorio {
	private int id;
    private String codpc;
    private String leito;
    private String idade;
    private String dataNascimento;
    private String diag;
    private String cid;
    private String medic;
    private String cuid;
    private String histmed;
    private String prog;

	public Relatorio(String codpc, String idade, String leito, String dataNascimento, String diag, String cid,
			String medic, String cuid, String histmed, String prog) {
		this.codpc = codpc;
		this.idade = idade;
		this.leito = leito;
		this.dataNascimento = dataNascimento;
		this.diag = diag;
		this.cid = cid;
		this.medic = medic;
		this.cuid = cuid;
		this.histmed = histmed;
		this.prog = prog;
		// TODO Auto-generated constructor stub
	}
	public Relatorio() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodpc() {
		return codpc;
	}
	public void setCodpc(String codpc) {
		this.codpc = codpc;
	}
	public String getLeito() {
		return leito;
	}
	public void setLeito(String leito) {
		this.leito = leito;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getDiag() {
		return diag;
	}
	public void setDiag(String diag) {
		this.diag = diag;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getMedic() {
		return medic;
	}
	public void setMedic(String medic) {
		this.medic = medic;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getHistmed() {
		return histmed;
	}
	public void setHistmed(String histmed) {
		this.histmed = histmed;
	}
	public String getProg() {
		return prog;
	}
	public void setProg(String prog) {
		this.prog = prog;
	

	}
}