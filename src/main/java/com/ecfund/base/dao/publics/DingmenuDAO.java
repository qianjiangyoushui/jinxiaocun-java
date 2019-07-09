package com.ecfund.base.dao.publics;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.publics.Dingmenu;
import com.ecfund.base.model.publics.Dingrm;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DingmenuDAO extends BaseDAO<Dingmenu> {


    public List<Dingmenu> findMenuByRole(Dingrm dingrm) {
        return sqlSessionTemplate.selectList(Dingmenu.class.getSimpleName()
                + ".findMenus", dingrm);
    }
}