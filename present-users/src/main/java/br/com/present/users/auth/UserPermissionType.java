package br.com.present.users.auth;

import br.com.present.auths.config.type.permissions.IPermissionType;
import br.com.present.auths.config.type.roles.RolesType;
import br.com.present.commons.util.PresentCollectionUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPermissionType implements IPermissionType {
    USER_READ("('user:read')"),
    USER_WRITE("('user:write')");

    final String value;

    static {
        RolesType.ADMIN.addPermissions(
           PresentCollectionUtils.newHashSet(UserPermissionType.values())
        );

        RolesType.PROF.addPermissions(
           PresentCollectionUtils.newHashSet(
              UserPermissionType.USER_READ
           )
        );

        RolesType.ALUNO.addPermissions(
           PresentCollectionUtils.newHashSet(
              UserPermissionType.USER_READ
           )
        );
    }
}

