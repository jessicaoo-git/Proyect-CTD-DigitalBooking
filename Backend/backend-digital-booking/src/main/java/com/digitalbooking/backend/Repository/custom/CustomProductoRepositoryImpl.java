package com.digitalbooking.backend.Repository.custom;

import com.digitalbooking.backend.Filtros.FiltroProductos;
import com.digitalbooking.backend.Models.Producto;
import com.digitalbooking.backend.Models.Reserva;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Component
public class CustomProductoRepositoryImpl implements  CustomProductoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CustomPage<Producto> findByFilters(FiltroProductos filtros,Pageable pageable) {

        CustomPage<Producto> customPage = new CustomPage<Producto>();

        //Query para obtener los productos filtrados
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
        Root<Producto> productoRoot = cq.from(Producto.class);
        buildQueryWithFilters(filtros, cb, cq, productoRoot);
        List<Producto> result =
                entityManager
                        .createQuery(cq)
                        .setMaxResults(pageable.getPageSize())
                        .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                        .getResultList();
        customPage.setContent(result);

        //Query para obtener la cantidad total de productos ya filtrados
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Producto> root = query.from(Producto.class);
        query.select(cb.count(root));
        buildQueryWithFilters(filtros, cb, query, root);
        long cantidad =entityManager.createQuery(query).getSingleResult();
        customPage.setTotalElements(cantidad);

        return customPage;
    }

    private void buildQueryWithFilters
            (FiltroProductos filtros, CriteriaBuilder cb, CriteriaQuery<?> cq, Root<?> root) {

        Predicate restrictions = cb.conjunction();
        if (filtros.hasCiudadId())
            restrictions = cb.and(restrictions, cb.equal(root.get("ciudad").get("id"), filtros.getCiudadId()));
        if (filtros.hasCategoriaId()) {
            restrictions = cb.and(restrictions, cb.equal(root.get("categoria").get("id"), filtros.getCategoriaId()));
        }
        if(filtros.hasPeriodo()) {
            Subquery<Integer> sub =cq.subquery(Integer.class);
            Root<Reserva> reservaRoot = sub.from(Reserva.class);
            sub.select(reservaRoot.get("producto").get("id"))
                    .where(cb.and(
                            cb.between(reservaRoot.get("fechaInicial"),
                                    filtros.getFechaInicio(),
                                    filtros.getFechaFin()),
                            cb.between(reservaRoot.get("fechaFinal"),
                                    filtros.getFechaInicio(),
                                    filtros.getFechaFin())));
            restrictions = cb.and(restrictions,cb.not(cb.in(root.get("id")).value(sub)));
        }
        cq.where(restrictions);
    }
}
