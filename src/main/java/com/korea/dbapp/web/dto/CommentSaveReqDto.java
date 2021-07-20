package com.korea.dbapp.web.dto;

import lombok.Data;


@Data
public class CommentSaveReqDto {
	private String text;
	private int postId;
}
