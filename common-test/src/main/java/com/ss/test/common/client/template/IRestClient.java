package com.ss.test.common.client.template;

import com.ss.playo.common.interfaces.IDto;

public interface IRestClient<T extends IDto>  {

//    IMarshaller getMarshaller();

    String getUri();
}
