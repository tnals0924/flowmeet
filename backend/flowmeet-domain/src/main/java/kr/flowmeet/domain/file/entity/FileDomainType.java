package kr.flowmeet.domain.file.entity;

public enum FileDomainType {
    NODE_ATTACHMENT, //노드 첨부파일
    NODE_NOTE_IMAGE, //노드 노트 에디터 내 이미지
    MEETING_SUMMARY, //회의 요약 관련 파일
    CHAT_ATTACHMENT, //AI 채팅 첨부파일
    TEMP //업로드 직후 임시 상태
}
