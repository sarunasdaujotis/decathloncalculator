<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Decathlon athletes and standings</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th style="text-align:left">Full name</th>
                        <th style="text-align:left">Place</th>
                        <th style="text-align:left">Score</th>
                        <th style="text-align:left">Run 100m</th>
                        <th style="text-align:left">Long jump</th>
                        <th style="text-align:left">Shot put</th>
                        <th style="text-align:left">High jump</th>
                        <th style="text-align:left">Run 400m</th>
                        <th style="text-align:left">Run 110m</th>
                        <th style="text-align:left">Discus throw</th>
                        <th style="text-align:left">Pole vault</th>
                        <th style="text-align:left">Javelin throw</th>
                        <th style="text-align:left">Run 1500m</th>
                    </tr>
                    <xsl:for-each select="competition/athlete">
                        <tr>
                            <td>
                                <xsl:value-of select="fullName"/>
                            </td>
                            <td>
                                <xsl:value-of select="place"/>
                            </td>
                            <td>
                                <xsl:value-of select="score"/>
                            </td>
                            <xsl:for-each select="eventResult">
                            <td>
                            Score: <xsl:value-of select="score"/><br/>Result: <xsl:value-of select="result"/>
                            </td>
                            </xsl:for-each>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>