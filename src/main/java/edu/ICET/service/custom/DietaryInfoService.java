package edu.ICET.service.custom;

import edu.ICET.dto.DietaryInfo;
import edu.ICET.service.SuperService;
import jakarta.validation.constraints.NotNull;

public interface DietaryInfoService extends SuperService<DietaryInfo> {
    void setCalculatedData(@NotNull(message = "Diet info can't be empty") DietaryInfo dietaryInfo);
}
