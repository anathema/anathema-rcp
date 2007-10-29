<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="ISO-8859-1" indent="yes"/>
	
	<xsl:param name="bundleVersion" />
	
	<xsl:template match="/">
		<hierarchy bundleVersion="{$bundleVersion}"> 
			<xsl:copy-of select="Series/Plot"/>
		</hierarchy>
	</xsl:template>
</xsl:stylesheet>		