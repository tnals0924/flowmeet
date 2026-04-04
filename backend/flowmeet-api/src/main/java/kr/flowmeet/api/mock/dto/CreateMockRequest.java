package kr.flowmeet.api.mock.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateMockRequest(
        @NotBlank(message = "이름은 필수로 입력해 주세요.")
        String name,
        @NotBlank(message = "설명은 필수로 입력해 주세요.")
        String description
) {
}
