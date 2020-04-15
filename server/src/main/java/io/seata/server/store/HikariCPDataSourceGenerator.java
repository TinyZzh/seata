/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.server.store;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.seata.common.loader.LoadLevel;
import io.seata.core.store.db.AbstractDataSourceGenerator;

import javax.sql.DataSource;

/**
 * The type <a href="https://github.com/brettwooldridge/HikariCP">HikariCP</a>  data source generator.
 *
 * @author tinyz
 */
@LoadLevel(name = "hakaricp")
public class HikariCPDataSourceGenerator extends AbstractDataSourceGenerator {

    @Override
    public DataSource generateDataSource() {
        HikariConfig config = new HikariConfig();
        config.setPoolName("seata-hikaricp");
        config.setDriverClassName(getDriverClassName());
        config.setJdbcUrl(getUrl());
        config.setUsername(getUser());
        config.setPassword(getPassword());

        config.setMaximumPoolSize(getMaxConn());
        config.setMinimumIdle(getMinConn());

        config.setConnectionTestQuery(getValidationQuery(getDBType()));
        config.setMaxLifetime(300000);
        config.setConnectionTimeout(10000);
        config.setIdleTimeout(120000);
        config.setLeakDetectionThreshold(300000);
        config.setAutoCommit(true);
        return new HikariDataSource(config);
    }
}
