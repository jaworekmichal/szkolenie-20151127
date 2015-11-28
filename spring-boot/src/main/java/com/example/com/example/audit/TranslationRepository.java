package com.example.com.example.audit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<TranslationLog, Long> {
}
