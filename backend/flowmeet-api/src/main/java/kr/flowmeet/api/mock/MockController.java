package kr.flowmeet.api.mock;

import kr.flowmeet.api.common.dto.CommonResponse;
import kr.flowmeet.api.mock.dto.CreateMockRequest;
import kr.flowmeet.api.mock.dto.GetAllMocksResponse;
import kr.flowmeet.auth.annotation.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/mock")
@RequiredArgsConstructor
public class MockController {

    private final MockFacade mockFacade;

    @PostMapping
    public CommonResponse<?> createMock(@UserId Long userId, @RequestBody CreateMockRequest request) {
        mockFacade.createMock(userId, request);
        return CommonResponse.ok();
    }

    @GetMapping
    public CommonResponse<GetAllMocksResponse> getAllMocks(@UserId Long userId) {
        return CommonResponse.ok(mockFacade.getAllMocks(userId));
    }
}
