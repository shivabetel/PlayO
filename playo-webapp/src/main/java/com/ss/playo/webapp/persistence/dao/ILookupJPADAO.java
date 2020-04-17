package com.ss.playo.webapp.persistence.dao;

import com.ss.playo.common.interfaces.dao.IJPADAO;
import com.ss.playo.webapp.persistence.dao.model.Lookup;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILookupJPADAO extends IJPADAO<Lookup, Long> {

    @Query("select lookup from Lookup lookup")
   List<Lookup> queryAll();

}
