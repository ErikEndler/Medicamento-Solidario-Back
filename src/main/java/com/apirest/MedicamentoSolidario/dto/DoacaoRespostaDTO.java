package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.apirest.MedicamentoSolidario.Models.Doacao;
import com.apirest.MedicamentoSolidario.Models.Medicamento;

public class DoacaoRespostaDTO {

	private long id;
	private LocalDateTime dataDoacao;
	private String obs;
	private long idDoador;
	private long idVoluntario;
	private long idPonto;
	private List<MedicamentoRespostaDTO> medicamentos;

	public DoacaoRespostaDTO(long id, LocalDateTime dataDoacao, String obs, long idDoador, long idVoluntario,
			List<Medicamento> lista, long idPonto) {
		super();

		this.id = id;
		this.setDataDoacao(dataDoacao);
		this.obs = obs;
		this.idDoador = idDoador;
		this.idVoluntario = idVoluntario;
		this.idPonto = idPonto;
		List<MedicamentoRespostaDTO> listaMed = new ArrayList<MedicamentoRespostaDTO>();
		for (Medicamento medicamento : lista) {
			listaMed.add(MedicamentoRespostaDTO.transformaEmDTOList(medicamento));
		}
		this.setMedicamentos(listaMed);
	}

	public static DoacaoRespostaDTO transformaEmDTO(Doacao doacao) {
		return new DoacaoRespostaDTO(doacao.getId(), doacao.getDataDoacao(), doacao.getObs(),
				doacao.getDoador().getId(), doacao.getVoluntario().getId(), doacao.getMedicamento(),
				doacao.getPonto().getId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public long getIdDoador() {
		return idDoador;
	}

	public void setIdDoador(long idDoador) {
		this.idDoador = idDoador;
	}

	public long getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(long idPonto) {
		this.idPonto = idPonto;
	}

	public long getIdVoluntario() {
		return idVoluntario;
	}

	public void setIdVoluntario(long idVoluntario) {
		this.idVoluntario = idVoluntario;
	}

	public List<MedicamentoRespostaDTO> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<MedicamentoRespostaDTO> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public LocalDateTime getDataDoacao() {
		return dataDoacao;
	}

	public void setDataDoacao(LocalDateTime dataDoacao) {
		this.dataDoacao = dataDoacao;
	}

}
