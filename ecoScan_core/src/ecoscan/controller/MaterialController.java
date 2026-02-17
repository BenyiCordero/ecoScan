package ecoscan.controller;

import ecoscan.dao.MaterialDAO;
import ecoscan.model.Material;
import ecoscan.model.dto.MaterialRequest;
import ecoscan.model.dto.MaterialUpdate;
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
    public String createMaterial(MaterialRequest materialRequest) throws Exception {
        // validaciones
        if (materialRequest.nombreMaterial() == null || materialRequest.nombreMaterial().trim().isEmpty()) {
            throw new Exception("El nombre del material es obligatorio");
        }

        Material material = new Material(materialRequest.nombreMaterial());
        materialDAO.createMaterial(material);

        return "Material registrado correctamente";
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
    public String update(MaterialUpdate materialUpdate) throws Exception {
        // validaciones
        if (materialUpdate.idMaterial() == null || materialUpdate.idMaterial() <= 0) {
            throw new Exception("ID de material inválido");
        }
        if (materialUpdate.nombreMaterial() == null || materialUpdate.nombreMaterial().trim().isEmpty()) {
            throw new Exception("El nombre del material es obligatorio");
        }

        // verificar que existe
        Material existente = materialDAO.getById(materialUpdate.idMaterial());
        if (existente == null) {
            throw new Exception("Material no encontrado");
        }

        // actualizar
        existente.setNombreMaterial(materialUpdate.nombreMaterial());
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
            throw new Exception("Material no encontrado");
        }

        materialDAO.delete(idMaterial);

        return "Material eliminado correctamente";
    }

}
