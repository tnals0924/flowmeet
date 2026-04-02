package kr.flowmeet.api.mock.dto;

import java.util.List;

public record GetAllMocksResponse(
        List<MockItem> mocks
) {
    public static GetAllMocksResponse of(final List<String> mocks) {
        return new GetAllMocksResponse(mocks.stream().map(MockItem::from).toList());
    }

    public record MockItem(
            String name
    ) {
        public static MockItem from(final String name) {
            return new MockItem(name);
        }
    }
}
