<#import "/spring.ftl" as spring />
<#assign home_url><@spring.url relativeUrl="/"/></#assign>
<#assign company_url><@spring.messageText code="company.url" text=companyUrl!"http://www.springsource.com/"/></#assign>
<#assign company_name><@spring.messageText code="company.name" text=companyName!"SpringSource"/></#assign>
<div id="header">
	<div id="name-and-company">
		<div id='site-name'>
			<a href="http://www.alain-com" title="Ciezala" rel="home">
				Ciezala
			</a>
		</div>
	</div> <!-- /name-and-company -->
</div> <!-- /header -->
