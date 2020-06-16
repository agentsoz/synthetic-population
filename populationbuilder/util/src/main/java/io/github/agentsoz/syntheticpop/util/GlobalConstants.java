package io.github.agentsoz.syntheticpop.util;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2020 by its authors. See AUTHORS file.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

/**
 * @author Bhagya N. Wickramasinghe
 */
public class GlobalConstants {

    public enum ExitCode {
        NORMAL(0, "All Good!"),
        IOERROR(1, "Input/output error"),
        ENVVAR(2, "Environment variable read error"),
        USERINPUT(3, "User input error"),
        PROGERROR(4, "Programmar error"),
        UNDEF(99, "Undefined error"),
        DATA_ERROR(5, "Error in population data");

        private int code;
        private String msg;

        ExitCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return this.code;
        }

        public String getMsg() {
            return this.msg;
        }
    }
}
