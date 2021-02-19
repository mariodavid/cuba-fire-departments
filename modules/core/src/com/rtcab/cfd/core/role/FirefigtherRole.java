package com.rtcab.cfd.core.role;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;
import com.rtcab.cfd.entity.*;

@Role(name = FirefigtherRole.NAME)
public class FirefigtherRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Firefigther";

    @ScreenAccess(screenIds = {"cfd_FireDepartment.browse", "application-cfd", "cfd_Employee.browse", "cfd_Maintenance.browse", "cfd_Equipment.browse", "cfd_Course.browse", "courseManagement", "cfd_CourseTopic.browse", "cfd_Course.edit", "cfd_CourseTopic.edit", "cfd_CourseTopicAttachment.edit", "cfd_Employee.edit", "cfd_Equipment.edit", "cfd_FireDepartment.edit", "fireLogin", "fireMainScreen", "cfd_Operation.browse", "cfd_Operation.edit", "cfd_OperationReport.edit", "cfd_OperationType.edit", "cfd_OperationType.browse", "ddcui$user-inbox", "ddcui$message-details", "ddcui$send-message"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Employee.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = OperationReport.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = OperationType.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Operation.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Maintenance.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = FireDepartment.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Equipment.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = CourseTopicAttachment.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = CourseTopic.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Course.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Employee.class, modify = "*")
    @EntityAttributeAccess(entityClass = OperationReport.class, modify = "*")
    @EntityAttributeAccess(entityClass = OperationType.class, modify = "*")
    @EntityAttributeAccess(entityClass = Operation.class, modify = "*")
    @EntityAttributeAccess(entityClass = Maintenance.class, modify = "*")
    @EntityAttributeAccess(entityClass = FireDepartment.class, modify = "*")
    @EntityAttributeAccess(entityClass = Equipment.class, modify = "*")
    @EntityAttributeAccess(entityClass = CourseTopicAttachment.class, modify = "*")
    @EntityAttributeAccess(entityClass = CourseTopic.class, modify = "*")
    @EntityAttributeAccess(entityClass = Course.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
