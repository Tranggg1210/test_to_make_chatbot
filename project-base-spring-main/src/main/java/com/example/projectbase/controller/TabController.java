package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.TabCreateDto;
import com.example.projectbase.domain.dto.request.TabRenameDto;
import com.example.projectbase.service.TabService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestApiV1
@Validated
public class TabController {
    private final TabService tabService;

    @Tags({@Tag(name = "tab-controller-admin"), @Tag(name = "tab-controller")})
    @Operation(summary = "API create tab")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping(UrlConstant.Tab.CREATE_TAB)
    public ResponseEntity<?> createTab(@RequestBody TabCreateDto tabCreateDto) {
        return VsResponseUtil.success(tabService.createTab(tabCreateDto));
    }

    @Tags({@Tag(name = "tab-controller-admin"), @Tag(name = "tab-controller")})
    @Operation(summary = "API get tab by user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(UrlConstant.Tab.GET_TABS)
    public ResponseEntity<?> getTabsByUserId(@RequestParam String userId) {
        return VsResponseUtil.success(tabService.getTabsByUserId(userId));
    }

    @Tags({@Tag(name = "tab-controller-admin"), @Tag(name = "tab-controller")})
    @Operation(summary = "API rename tab")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping(UrlConstant.Tab.RENAME_TAB)
    public ResponseEntity<?> renameTab(@RequestBody TabRenameDto tabRenameDto) {
        return VsResponseUtil.success(tabService.renameTab(tabRenameDto));
    }

    @Tag(name = "tab-controller-admin")
    @Operation(summary = "API delete tab")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(UrlConstant.Tab.DELETE_TAB)
    public ResponseEntity<?> DeleteTabById(@RequestParam String tabId) {
        return VsResponseUtil.success(tabService.deleteTab(tabId));
    }
}
