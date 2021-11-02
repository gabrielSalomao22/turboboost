package com.example.turboboost.grafico;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraficoDTO {

	private int qntDatas;
	private List<DadosDTO> dados;
}
