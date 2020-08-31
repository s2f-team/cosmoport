package com.space.service;

import com.space.model.ShipType;
import com.space.model.entity.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShipService {

    @Autowired
    ShipRepository shipRepository;

    public Page<Ship> listAll(Specification<Ship> specification, Pageable sortByName) {
        return shipRepository.findAll(specification, sortByName);
    }

    public Integer сount(Specification<Ship> specification) {
        return Math.toIntExact(shipRepository.count(specification));
    }

    public Optional<Ship> findById(Long id) {
        return shipRepository.findById(id);
    }

    public Ship save(Ship ship) {
        return shipRepository.save(ship);
    }

    public void deleteById(Long id) {
        shipRepository.deleteById(id);
    }

    public boolean isExistById(Long id) {
        return shipRepository.existsById(id);
    }

    // Filters

    public Specification<Ship> sortByName(String name) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (name == null) return null;
                return criteriaBuilder.like(root.get("name"), "%" + name + "%");
            }
        };
    }

    public Specification<Ship> sortByPlanet(String planet) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (planet == null) return null;
                return criteriaBuilder.like(root.get("planet"), "%" + planet + "%");
            }
        };
    }

    public Specification<Ship> sortByShipType(ShipType shipType) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (shipType == null) return null;
                return criteriaBuilder.equal(root.get("shipType"), shipType);
            }
        };
    }

    public Specification<Ship> sortByProdDate(Long before, Long after) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (before == null && after == null) {
                    return null;
                }
                if (after == null) {
                    Date beforeDate = new Date(before);
                    return criteriaBuilder.lessThanOrEqualTo(root.get("prodDate"), beforeDate);
                }
                if (before == null) {
                    Date afterDate = new Date(after);
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("prodDate"), afterDate);
                }
                Date beforeDate = new Date(before);
                Date afterDate = new Date(after);
                return criteriaBuilder.between(root.get("prodDate"), beforeDate, afterDate);
            }
        };
    }

    public Specification<Ship> sortByUsed(Boolean isUsed) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (isUsed == null) return null;
                if (isUsed) return criteriaBuilder.isTrue(root.get("isUsed"));
                else return criteriaBuilder.isFalse(root.get("isUsed"));
            }
        };
    }

    public Specification<Ship> sortBySpeed(Double minSpeed, Double maxSpeed) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (minSpeed == null && maxSpeed == null) return null;
                if (minSpeed == null) return criteriaBuilder.lessThanOrEqualTo(root.get("speed"), maxSpeed);
                if (maxSpeed == null) return criteriaBuilder.greaterThanOrEqualTo(root.get("speed"), minSpeed);
                return criteriaBuilder.between(root.get("speed"), minSpeed, maxSpeed);
            }
        };
    }

    public Specification<Ship> sortByCrewSize(Integer minSize, Integer maxSize) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (minSize == null && maxSize == null) return null;
                if (minSize == null) return criteriaBuilder.lessThanOrEqualTo(root.get("crewSize"), maxSize);
                if (maxSize == null) return criteriaBuilder.greaterThanOrEqualTo(root.get("crewSize"), minSize);
                return criteriaBuilder.between(root.get("crewSize"), minSize, maxSize);
            }
        };
    }

    public Specification<Ship> sortByRating(Double minRating, Double maxRating) {
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (minRating == null && maxRating == null) return null;
                if (minRating == null) return criteriaBuilder.lessThanOrEqualTo(root.get("rating"), maxRating);
                if (maxRating == null) return criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), minRating);
                return criteriaBuilder.between(root.get("rating"), minRating, maxRating);
            }
        };
    }


}
