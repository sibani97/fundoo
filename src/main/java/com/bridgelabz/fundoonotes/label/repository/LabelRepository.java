package com.bridgelabz.fundoonotes.label.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.labels.model.Labels;





@Repository
public interface LabelRepository extends JpaRepository<Labels,Long>{
public Labels findByLabelId(Long labelId);
}
