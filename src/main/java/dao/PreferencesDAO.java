package dao;

import java.util.List;

import core.Core;
import jpa.entidades.Preferences;

public class PreferencesDAO {

	public static List<Preferences> getPreferencesById(int id) {
		List<Preferences> preferences = Core.manager.createQuery("SELECT preferencia FROM Preferences preferences WHERE id_cliente = " + id, Preferences.class).getResultList();
		return preferences;		
	}
}
