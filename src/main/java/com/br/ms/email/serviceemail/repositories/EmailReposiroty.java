package com.br.ms.email.serviceemail.repositories;

import com.br.ms.email.serviceemail.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailReposiroty extends JpaRepository<EmailModel, UUID> {


}
