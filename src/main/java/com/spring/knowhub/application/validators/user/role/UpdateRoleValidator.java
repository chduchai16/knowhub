package com.spring.knowhub.application.validators.user.role;

import com.spring.knowhub.application.commands.user.role.UpdateRoleCommand;
import com.spring.knowhub.application.exceptions.user.role.UpdateRoleException;

import java.util.HashSet;

public class UpdateRoleValidator {
    public static void validate (UpdateRoleCommand command){
        if(command.getRoleId() == null ){
           throw UpdateRoleException.missingRequiredFields("roleID");
        }
        if(command.getRoleId() <= 0) {
            throw UpdateRoleException.invalidField("roleID", "Phải là số dương");
        }
        if(command.getName() == null || command.getName().isEmpty()) {
            throw UpdateRoleException.missingRequiredFields("name");
        }
        if(command.getName().length() < 3 || command.getName().length() > 50) {
            throw UpdateRoleException.invalidField("name", "Độ dài phải từ 3 đến 50 ký tự");
        }
        if(command.getPermissionIds() == null) {
            command.setPermissionIds(new HashSet<>());
        }
    }

}
