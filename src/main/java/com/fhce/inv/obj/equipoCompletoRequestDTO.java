package com.fhce.inv.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class equipoCompletoRequestDTO {
    private equipoRequestDTO equipoRequestDTO;
    private componentePcRequestDTO componentePcRequestDTO;
    private perteneceRequestDTO perteneceRequestDTO;
    private redRequestDTO redRequestDTO;
    private ubicacionRequestDTO ubicacionRequestDTO;
    private softwareRequestDTO softwareRequestDTO;
}