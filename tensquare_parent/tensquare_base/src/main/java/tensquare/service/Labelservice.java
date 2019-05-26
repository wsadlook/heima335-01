package tensquare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tensquare.dao.LabelDao;
import tensquare.pojo.Label;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class Labelservice {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public Label findById(String labelId) {
        return labelDao.findById(labelId).get();
    }

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    @Transactional
    public void save(Label label) {
        String id = idWorker.nextId() + "";

        label.setId(id);

        labelDao.save(label);
    }

    public void updateById(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String labelId) {
        labelDao.deleteById(labelId);

    }

    public List<Label> search(Label label) {
        Specification<Label> specification = getSpecification(label);


        List<Label> list = labelDao.findAll(specification);
        return list;
    }

    private Specification<Label> getSpecification(Label label) {
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();

                if (label.getId() != null && !"".equals(label.getId())) {
                    Predicate labelid = cb.equal(root.get("id").as(String.class),  label.getId() );
                    predicateList.add(labelid);
                }

                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate labelname = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(labelname);
                }

                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate labelstate = cb.equal(root.get("state").as(String.class),  label.getState() );
                    predicateList.add(labelstate);
                }

                if (label.getCount() != null ) {
                    Predicate labelcount = cb.equal(root.get("count").as(Long.class),  label.getCount());
                    predicateList.add(labelcount);
                }

                if (label.getRecommend() != null && !"".equals(label.getRecommend())) {
                    Predicate labelrecommend = cb.equal(root.get("recommend").as(String.class),  label.getRecommend() );
                    predicateList.add(labelrecommend);
                }

                if (label.getFans() != null ) {
                    Predicate labelfans = cb.equal(root.get("count").as(Long.class),  label.getFans());
                    predicateList.add(labelfans);
                }


                Predicate[] predicates = new Predicate[predicateList.size()];

                predicates = predicateList.toArray(predicates);

                return cb.and(predicates);
            }
        };
    }

    public Page<Label> search(Label label, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);

        Specification<Label> specification = getSpecification(label);
        Page<Label> pageData =   labelDao.findAll(specification,pageRequest);
        return pageData;
    }
}
