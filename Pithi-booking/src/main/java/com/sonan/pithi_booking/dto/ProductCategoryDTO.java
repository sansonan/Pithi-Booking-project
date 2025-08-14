package com.sonan.pithi_booking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategoryDTO {
    private Long id;
    @NotBlank
    private String categoryName;

}
