package com.hosmerlake.rss.common.content;



public interface ContentResponse<E> {

	E buildResponse(String head, String body);

	E buildEmptyResponse();

}
