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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import model.Master;
import model.Student;

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
		if (empty) {
			sql = "SELECT m FROM Master m";
		}
		Query q1 = em.createQuery(sql);
		list = q1.getResultList();
		return list;
	}

	public Master getMaster(int masterId) {
		Master master = new Master();
		master = em.find(Master.class, masterId);
		return master;
	}

	public Student getStudent(int nia) {
		Student student = new Student();
		student = em.find(Student.class, nia);
		return student;
	}

	public boolean UpdateMasterInscriptions(int nia, int masterId, boolean insert)
			throws NotSupportedException, SystemException { //If is not insert, it's delete
		boolean fail = true;
		try {
			ut.begin();
			Student student = getStudent(nia);
			Master master = getMaster(masterId);
			if (student != null) {
				List<Student> inscriptions = master.getStudents();
				List<Master> masterIns = student.getMasters();
				if (insert) {
					if (!contains(inscriptions, student)) {// Don't insert a student already inserted
						inscriptions.add(student);
						masterIns.add(master);
						fail = false;
					}
				} else {
					if (contains(inscriptions, student)) {// Don't delete a student which isn't inserted
						inscriptions.remove(student);
						masterIns.remove(master);
						fail = false;
					}
				}
				master.setStudents(inscriptions);
				em.merge(master);
				student.setMasters(masterIns);
				em.merge(student);
			}
			ut.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fail;

	}

	private boolean contains(List<Student> inscriptions, Student student) {
		boolean contains = false;
		for (Student studentInList : inscriptions) {
			if (studentInList.getNia() == student.getNia())
				contains = true;
		}
		return contains;
	}

}
