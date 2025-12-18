package com.github.novabank.presentation.Docs;

/**
 * API Layer Documentation
 *
 * Responsibilities:
 * - Expose REST endpoints
 * - Convert JSON <-> DTOs
 * - Orchestrate request flow
 * - Handle errors and logging
 *
 *
 * Controllers depend on:
 * - DTOs
 * - Service interfaces
 *
 * Error handling is centralized via GlobalExceptionHandler.
 */
public final class ApiDocumentation {
    private ApiDocumentation() {}
}
