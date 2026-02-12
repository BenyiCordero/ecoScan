package ecoscan.controller;

import ecoscan.dao.MaterialDAO;
import ecoscan.model.Material;
import java.util.List;
/**
 *
 * @author emont
 */
public class MaterialController {

    // se crea una sola vez para no estar repetiendo lo mismo
    private MaterialDAO materialDAO;

    public MaterialController() {
        this.materialDAO = new MaterialDAO();
    }

    // dar de alta material
    public String register(String nombreMaterial) throws Exception {
        // validaciones
        if (nombreMaterial == null || nombreMaterial.trim().isEmpty()) {
            throw new Exception("El nombre del material es obligatorio");
        }

        Material material = new Material(nombreMaterial);
        materialDAO.register(material);

        return "Material registrado correctamente con ID: " + material.getIdMaterial();
    }

    // traer todos los materiales
    public List<Material> getAll() throws Exception {
        return materialDAO.getAll();
    }

    // traer un material por su id
    public Material getById(Integer idMaterial) throws Exception {
        if (idMaterial == null || idMaterial <= 0) {
            throw new Exception("ID de material inválido");
        }

        Material material = materialDAO.getById(idMaterial);

        if (material == null) {
            throw new Exception("Material no encontrado con ID: " + idMaterial);
        }

        return material;
    }

    // actualizar material
    public String update(Integer idMaterial, String nombreMaterial) throws Exception {
        // validaciones
        if (idMaterial == null || idMaterial <= 0) {
            throw new Exception("ID de material inválido");
        }
        if (nombreMaterial == null || nombreMaterial.trim().isEmpty()) {
            throw new Exception("El nombre del material es obligatorio");
        }

        // verificar que existe
        Material existente = materialDAO.getById(idMaterial);
        if (existente == null) {
            throw new Exception("Material no encontrado con ID: " + idMaterial);
        }

        // actualizar
        existente.setNombreMaterial(nombreMaterial);
        materialDAO.update(existente);

        return "Material actualizado correctamente";
    }

    // eliminar material
    public String delete(Integer idMaterial) throws Exception {
        if (idMaterial == null || idMaterial <= 0) {
            throw new Exception("ID de material inválido");
        }

        // verificar que existe
        Material existente = materialDAO.getById(idMaterial);
        if (existente == null) {
            throw new Exception("Material no encontrado con ID: " + idMaterial);
        }

        materialDAO.delete(idMaterial);

        return "Material eliminado correctamente";
    }

}
