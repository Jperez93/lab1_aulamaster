package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import model.Master;

public class MasterModel {
	private EntityManager em;

	private UserTransaction ut;

	public MasterModel(EntityManager em, UserTransaction ut) {
		this.em = em;
		this.ut = ut;
	}

	public List<Master> getMastersList() throws Exception {
		List<Master> list = new ArrayList<>();
		try {
			Query q1 = em.createNamedQuery("Master.findAll", Master.class);
			list = q1.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Master> getMastersList(HashMap<String, String> parameters) throws Exception {
		List<Master> list = new ArrayList<>();
		String sql = "SELECT m FROM Master m WHERE ";
		boolean empty = true;
		for (Map.Entry<String, String> parameter : parameters.entrySet()) {
			if (!parameter.getValue().isEmpty()) {
				sql += "m." + parameter.getKey() + " = \"" + parameter.getValue() + "\" AND ";
				empty = false;
			}
		}
		sql = sql.substring(0, sql.length() - 5);
		if(empty) {
			sql = "SELECT m FROM Master m";
		}
		Query q1 = em.createQuery(sql);
		list = q1.getResultList();
		return list;
	}

}
