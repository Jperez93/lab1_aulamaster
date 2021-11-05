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

	/**
	 * Not used. Returns a list with all the registers in the database
	 * 
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * Given a HashMap, generates a dynamic query with the parameters that haven't
	 * been set to null and returns all the registers with the same set of
	 * attributes.
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 * 
	 */
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

	/**
	 * Given a masterid, it returns a master instance with the information of the
	 * register with the same masterid
	 * 
	 * @param masterId
	 * @return
	 */
	public Master getMaster(int masterId) {
		Master master = new Master();
		master = em.find(Master.class, masterId);
		return master;
	}

	/**
	 * Given a nia, it returns a student instance with the information of the
	 * register with the same nia
	 * 
	 * @param nia
	 * @return
	 */
	public Student getStudent(int nia) {
		Student student = new Student();
		student = em.find(Student.class, nia);
		return student;
	}

	/**
	 * Given a nia, a masterId, and a boolean which indicates the operation
	 * insert(true), delete(false). It gets the list of masters in which the student
	 * is inscribed and the list of students who are inscribed in the master. It
	 * adds/removes the master to/from the list of masters and do the same with the
	 * student in the list of students. In this way, it creates/destroy a relation
	 * between the student and the master by updating the table 'inscriptions'
	 * 
	 * @param nia
	 * @param masterId
	 * @param insert
	 * @return
	 * @throws NotSupportedException
	 * @throws SystemException
	 */
	public boolean UpdateMasterInscriptions(int nia, int masterId, boolean insert)
			throws NotSupportedException, SystemException { // If is not insert, it's delete
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

	/**
	 * 
	 * @param inscriptions
	 * @param student
	 * @return true if the students is in the inscriptions list
	 */
	private boolean contains(List<Student> inscriptions, Student student) {
		boolean contains = false;
		for (Student studentInList : inscriptions) {
			if (studentInList.getNia() == student.getNia())
				contains = true;
		}
		return contains;
	}

}
