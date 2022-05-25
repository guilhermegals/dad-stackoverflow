import * as Styles from './styles'
import mock from '../../atoms/mock/disciplinas.json'
import * as React from 'react'
import { Paper, MenuItem, MenuList, Checkbox, FormControlLabel, Divider, Typography } from '@mui/material'

export default class LeftMenu extends React.Component {
  static disciplinas
  static topic
  constructor() {
    super()
    function handleItemClick(event) {}
    const data = mock.disciplinas
    this.disciplinas = data.map((r) => (
      <MenuItem key={r.id} id={r.id} onClick={handleItemClick}>
        {r.name}
      </MenuItem>
    ))
    this.topic = data
      .find((element) => (element.id = '001'))
      .topics.map((item, index) => <FormControlLabel control={<Checkbox />} label={item} key={index} />)
  }

  render() {
    return (
      <Styles.Container>
        {/* <Typography style={{ margin: '20px', fontSize: '20px' }} mt={2}>
          Trending Topics
        </Typography>
        <Divider></Divider>
        <Paper>
          <MenuList>{this.disciplinas}</MenuList>
        </Paper> */}
      </Styles.Container>
    )
  }
}
