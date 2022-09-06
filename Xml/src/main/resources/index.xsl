<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Courses</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th align="left">Name</th>
                        <th align="left">Teacher</th>
                        <th align="left">Room</th>
                        <th align="left">Publication</th>
                    </tr>
                    <xsl:for-each select="courses/course">
                        <tr>
                            <td><xsl:value-of select="@name"/></td>
                            <td><xsl:value-of select="teacher"/></td>
                            <td><xsl:value-of select="room"/></td>
                            <td><xsl:value-of select="publication"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>