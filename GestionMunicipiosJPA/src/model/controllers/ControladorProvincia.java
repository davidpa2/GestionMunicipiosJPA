package model.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entities.Municipio;
import model.entities.Provincia;

public class ControladorProvincia {
	
	//Declaración de la instacia como null para crear un Singleton
	private static ControladorProvincia instance = null;
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("GestionMunicipiosJPA");
	
		
	/**
	 * Creación del patrón Singleton para poder acceder a todos los métodos mediante su instacia
	 */
	public static ControladorProvincia getInstance() {
		//Si es la primera vez que se ejecuta, se inicializa la instacia
		if (instance == null) {
			instance = new ControladorProvincia();
		}
		return instance;
	}

	public ControladorProvincia() {
	}
	
	/**
	 * 
	 * @param filtro
	 * @return
	 */
	public List<Municipio> filtrarMunicipio(String filtro) {
		EntityManager em = factory.createEntityManager();
		Query q = em.createNativeQuery("select * from municipio where nombre like ?",Municipio.class);
		q.setParameter(1,"%" + filtro + "%");
		List<Municipio> list = (List<Municipio>) q.getResultList();
		em.close();
		
		return list;
	}
	
	/**
	 * 
	 * @param m
	 * @return
	 */
	public boolean guardar (Municipio m) {
		try {
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			//Si la id el nuevo registro es 0, se crea uno nuevo
			if (m.getId() == 0) {
				em.persist(m);
			}
			//Sino, se modifica
			else {
				em.merge(m);
			}
			em.getTransaction().commit();
			em.close();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}	
	
	/**
	 * Método utilizado para buscar todos los campos de Municipio
	 * @return
	 */
	public List<Municipio> findAllMunicipios () {
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM municipio", Municipio.class);
		
		List<Municipio> list = (List<Municipio>) q.getResultList();
		em.close();
		return list;
	}
	
	/**
	 * Método utilizado para buscar todos los campos de Provincia
	 * @return
	 */
	public List<Provincia> findAllProvincias () {
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM provincia", Provincia.class);
		
		List<Provincia> list = (List<Provincia>) q.getResultList();
		em.close();
		return list;
	}	

}
